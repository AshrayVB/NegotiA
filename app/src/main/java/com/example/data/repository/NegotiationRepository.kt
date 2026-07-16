package com.example.data.repository

import com.example.data.local.NegotiationDao
import com.example.data.local.NegotiationEntity
import com.example.data.remote.Content
import com.example.data.remote.GenerateContentRequest
import com.example.data.remote.Part
import com.example.data.remote.RetrofitClient
import kotlinx.coroutines.flow.Flow
import com.example.BuildConfig

class NegotiationRepository(private val dao: NegotiationDao) {

    val allNegotiations: Flow<List<NegotiationEntity>> = dao.getAllNegotiations()

    fun getNegotiation(id: Int): Flow<NegotiationEntity?> = dao.getNegotiationById(id)

    suspend fun insert(negotiation: NegotiationEntity): Long = dao.insertNegotiation(negotiation)

    suspend fun delete(id: Int) = dao.deleteNegotiationById(id)

    suspend fun analyzeNegotiation(type: String, details: String): String {
        val apiKey = BuildConfig.GEMINI_API_KEY
        val prompt = """
            You are an expert negotiation coach. Analyze the following details for a $type negotiation.
            Extract key points, strong/weak areas, and provide a strategic counter-offer strategy.
            Keep it structured, clear, and professional.
            
            Details:
            $details
        """.trimIndent()

        val request = GenerateContentRequest(
            contents = listOf(
                Content(parts = listOf(Part(text = prompt)))
            ),
            systemInstruction = Content(
                parts = listOf(Part("You are NegotiAI, an elite negotiation expert and advisor. Format your response cleanly without markdown wrappers around the entire thing."))
            )
        )

        return try {
            val response = RetrofitClient.service.generateContent(apiKey, request)
            response.candidates?.firstOrNull()?.content?.parts?.firstOrNull()?.text 
                ?: "I couldn't analyze this right now. Please try again."
        } catch (e: Exception) {
            "Analysis failed: ${e.message}. Please check your connection and API key."
        }
    }
}
